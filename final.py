from flask import Flask
from flask_pymongo import PyMongo
from bson.json_util import dumps
from bson.objectid import ObjectId
from flask import jsonify,request
from werkzeug.security import generate_password_hash,check_password_hash
import bcrypt
import json
import random


app = Flask(__name__)
app.secret_key = "secretkey"
app.config['MONGO_URI'] = "mongodb://127.0.0.1:27017/Users"
mongo = PyMongo(app)


@app.route('/addvaccine',methods=['POST']) 
def addvaccine():
    form = request.form
    patientId=form["id"]
    vac_name = form["vac_name"]
    vac_date = form["vac_date"]
    details = {"vaccine_name":vac_name, "vaccine_data":vac_date}
    mongo.db.patients.update({"id":patientId},{"$push" : {"vaccines":details}})
    mongo.db.vaccine_list.insert({"vac_name":vac_name,"vac_date":vac_date,"patient_id":patientId})
    return 'added'



@app.route('/vaccine_list',methods=['POST'])
def vaccine_list():
    result = []
    form=request.form
    a=mongo.db.vaccine_list.find({"patient_id":form["id"]},{"_id":0})
    for d in a:
        result.append(d) 
    print("1")
    print(result)
    return jsonify({"result":result}) 
 


@app.route('/book_lab_appointment',methods=['POST'])
def book_lab_appointment():
    form=request.form
    lab_id=form["lab_id"]
    lab_name=form["lab_name"]
    date=form["date"]
    time=form["time"]
    diagnosis=form["diagnosis"]
    doctor_id=form["doctor_id"]
    patient_id=form["id"]
    count=mongo.db.lab_appointments.count({"patient_id":patient_id}, {})
    appointment_id=patient_id+str(count)
    mongo.db.lab_appointments.insert({"lab_id":lab_id,"lab_name":lab_name,"date":date,"time":time,"diagnosis":diagnosis,"appointment_id":appointment_id,"doctor_id":doctor_id,"patient_id":patient_id})
    print("inserted")
    return 'inserted'  



@app.route('/book_doctor_appointment',methods=['POST'])
def book_doctor_appointment():
    form=request.form
    doctor_id=form["doctor_id"]
    doctor_name=form["doctor_name"]
    date=form["date"]
    time=form["time"]
    problem=form["problem"]
    patient_id=form["id"]
    patientInfo = {"patientid":patient_id, "illness":problem, "time":time, "date":date}
    mongo.db.doctor.update({"id":doctor_id}, {"$push":{"requests":patientInfo}})
    count=mongo.db.doctor_appointments.count({"patient_id":patient_id}, {})
    appointment_id=patient_id+str(count)
    mongo.db.doctor_appointments.insert({"doctor_id":doctor_id,"doctor_name":doctor_name,"date":date,"time":time,"problem":problem,"appointment_id":appointment_id,"status":"pending","patient_id":patient_id})
    print("inserted")
    return 'inserted'  
   


@app.route('/all')
def getType():
    result = []
    a=mongo.db.doctor_data.find({},{"_id":0})
    for d in a:
        result.append(d) 
    print("1")
    return jsonify({"result":result})



@app.route('/lab_reports',methods=['POST'])
def lab_reports():
    result = [] 
    form=request.form
    a=mongo.db.lab_appointments.find({"email":form["email"]},{"_id":0})
    for d in a:
        result.append(d) 
    print("1")
    print(result)
    return jsonify({"result":result}) 
    


@app.route('/doctor_appointment_history',methods=['POST'])
def doctor_appointment_history():
    result = [] 
    form=request.form
    a=mongo.db.doctor_appointments.find({"patient_id":form["id"]},{"_id":0})
    for d in a:
        result.append(d) 
    print("1")
    print(result)
    return jsonify({"result":result}) 
   


@app.route('/doctor_login',methods=['POST'])
def doctor_login():
    form=request.form
    email=form["email"]
    password=form["password"]
    if mongo.db.doctor.count({"email":email}, {}) == 1:
        x=mongo.db.doctor.find_one({"email":email})["password_hash"]
        if check_password_hash(x,password) :
          print("1")
          y=mongo.db.doctor.find_one({"email":email})["name"]
          myid = mongo.db.doctor.find_one({"email":email})["id"]
          return jsonify({"response":{"status":"approved","email":email,"id":myid}})
        else:
            print("2")
            return jsonify({"response":{"status":"wrong_password"}})
    else:
        print("3")
        return jsonify({"response":{"status":"not_signed_up"}})
         
    
    
@app.route('/lab_login',methods=['POST'])
def lab_login():
    form=request.form
    email=form["email"]
    password=form["password"]
    if mongo.db.lab.count({"email":email}, {}) == 1:
        x=mongo.db.lab.find_one({"email":email})["password_hash"]
        if check_password_hash(x,password) :
          print("1")
          y=mongo.db.lab.find_one({"email":email})["name"]
          labid = mongo.db.lab.find_one({"email":email})["id"]
          return jsonify({"response":{"status":"approved","email":email,"id":labid}})
        else:
            print("2")
            return jsonify({"response":{"status":"wrong_password"}})
    else:
        print("3")
        return jsonify({"response":{"status":"not_signed_up"}})



@app.route('/patient_login',methods=['POST'])
def patient_login():
    form=request.form
    email=form["email"]
    password=form["password"]
    if mongo.db.patients.count({"email":email}, {}) == 1:
        x=mongo.db.patients.find_one({"email":email})["password_hash"]
        if check_password_hash(x,password) :
          print("1")
          y=mongo.db.patients.find_one({"email":email})["name"]
          patid = mongo.db.patients.find_one({"email":email})["id"]
          return jsonify({"response":{"status":"approved","email":email,"id":patid}})
        else:
            print("2")
            return jsonify({"response":{"status":"wrong_password"}})
    else:
        print("3")
        return jsonify({"response":{"status":"not_signed_up"}})
      


@app.route('/doctor_signup',methods=['POST'])
def doctor_signup():
    form=request.form
    docid = random.randint(0,1000)
    docid = str(docid)
    name=form["name"]
    years_of_experience=form["years_of_experience"]
    practice_type=form["practice_type"]
    location=form["location"]
    email=form["email"]
    password=form["password"]
    password_hash=generate_password_hash(password)
    if mongo.db.doctor.count({"email":email}, {}) == 0:
     mongo.db.doctor.insert({"id":docid,"name":name,"location":location,"email":email,"password_hash":password_hash,"years_of_experience":years_of_experience,"practice_type":practice_type, "history":[], "appointments":[], "active":[], "requests":[]})
     print('inserted')
     return 'inserted'
    else:
        print('not inserted')
        return'email already present' 



@app.route('/lab_signup',methods=['POST'])
def lab_signup():
    form=request.form
    labid = random.randint(0,1000)
    labid = str(labid)
    name=form["name"]
    location=form["location"]
    email=form["email"]
    password=form["password"]
    password_hash=generate_password_hash(password)
    if mongo.db.lab.count({"email":email}, {}) == 0:
     mongo.db.lab.insert({"name":name,"location":location,"email":email,"password_hash":password_hash, "id":labid, "history":[], "pending":[]})
     print('inserted')
     return 'inserted'
    else:
        print('not inserted')
        return'email already present' 


    
@app.route('/patient_signup',methods=['POST'])
def patient_signup():
    patid = random.randint(0,1000)
    patid = str(patid)
    form=request.form
    name=form["name"]
    age=form["age"]
    blood_group=form["blood_group"]
    gender=form["gender"]
    location=form["location"]
    email=form["email"]
    password=form["password"]
    password_hash=generate_password_hash(password)
    if mongo.db.patients.count({"email":email}, {}) == 0:
     mongo.db.patients.insert({"name":name,"id":patid,"history":[],"pending":[],"age":age,"blood_group":blood_group,"gender":gender,"location":location,"email":email,"password_hash":password_hash, "vaccines":[], "reports":[], "current_reports":[]})
     print('inserted')
     return 'inserted'
    else:
        print('not inserted')
        return'email already present' 
              



@app.route('/add',methods=['POST'])
def add_user():
    _json = request.json
    _name = _json["name"]
    _email = _json['email']
    _password = _json['pwd']
    if _name and _email and _password and request.method =='POST':
        _hashed_password = generate_password_hash(_password)
        id = mongo.db.user.insert({'name':_name,'email':_email,'pwd':_hashed_password})
        resp = jsonify("User added successfully")
        resp.status_code = 200
        return resp
    else:
        return not_found()



@app.errorhandler(404)
def not_found(error = None):
    message = {
        'status':404,
        'message':'Not Found' + request.url
    }
    resp = jsonify(message)
    resp.status_code = 404
    return resp



@app.route('/users')
def users():
    users = mongo.db.user.find()
    resp = dumps(users)
    return resp



@app.route('/delete/<id>',methods=['DELETE'])
def delete_user(id):
    mongo.db.user.delete_one({'_id': ObjectId(id)})
    resp = jsonify("User Deleted")
    resp.status_code;
    return resp



@app.route('/user/<id>')
def user(id):
    user = mongo.db.user.find_one({'_id':ObjectId(id)})
    resp = dumps(user)
    return resp



@app.route('/update/<id>',methods=['PUT'])
def update_user(id):
    _id = id
    _json = request.json
    _name = _json['name']
    _email = _json['email']
    _password = _json['pwd']
    if _name and _email and _password and _id and request.method == 'PUT':
        _hashed_password = generate_password_hash(_password)
        mongo.db.user.update({'_id': ObjectId(_id['$oid']) if '$oid' in _id else ObjectId(_id)},{'$set':{'name':_name,'email':_email,'pwd':_hashed_password}})
        resp = jsonify("User updated")
        resp.status_code = 200
        return resp
    else:
        return not_found()




@app.route('/fileupload', methods = ['GET', 'POST'])
def handle_request():
    return '''
        <form method="POST" action="/upload" enctype="multipart/form-data">
            <input type="text" name="name">
            <input type="file" name="image">
            <input type="submit">
        </form>
    '''



@app.route('/file/<filename>')
def file(filename):
    return mongo.send_file(filename)



@app.route('/reports/<pat_id>',methods=['GET'])
def reports(pat_id):
    user = mongo.db.patients.find_one({"id":pat_id})
    reports = user["reports"]
    rep_names = []
    for x in reports:
        rep_names.append(x["name"])
    resp = {"reports":rep_names}
    resp = dumps(resp)
    return resp



@app.route('/accept',methods=['POST'])
def accept():                                                                                                                                                                                                                                                              
    user = "user1"
    json = request.json
    name=json["name"]
    labid = json["labid"]
    con = mongo.db.labuser.find_one({"id":labid})
    pending = con["pending"]
    users = mongo.db.patients.find_one({"id":name})
    pending.append(users)
    mongo.db.labuser.update({"name":user},{"$set":{"pending":pending}})
    return "request sent"



@app.route('/uploadpd',methods=['POST'])
def uploadpd():
    json = request.json
    filename = json["filename"]
    pid = json["pid"]
    labid = json["labid"]
    return "hi"



@app.route('/upload',methods=['POST'])
def upload():
    data = request.files
    json = request.json
    print(data)
    print(json)
    print("kfnsdsak")
    if 'image' in request.files:
        file1 = request.files["image"]
        mongo.save_file(file1.filename, file1)
    elif 'filename' in request.files:    
        file1 = request.files["filename"]
        mongo.save_file(file1.filename, file1)
    return "done"




@app.route('/getlist/<id>',methods=['GET'])
def getlist(id):
    json = request.json
    dic = {"pending":[{"id":"1234","Name":"user","reports":"Blood-HBG"},
		{"id":"1234","Name":"user","reports":"Sugar-level"},
		{"id":"1234","Name":"user","reports":"LHT"},
        {"id":"2234","Name":"user","reports":"Blood-HBG"}]}
    resp = dumps(dic) 
    return resp  



@app.route('/history/<id>',methods=['GET']) 
def history(id):
    json = request.json
    dic={"history":[{"id":"1234","Name":"user","reports":"Blood-HBG","filename":"438.pdf"}]}
    resp = dumps(dic) 
    return resp



@app.route('/labdata/<id>',methods=['GET'])
def labdata(id): 
    json = request.json
    labId=id
    dic = {"Data":{"Name":"Name","email":"email","Location":"location"}}
    resp = dumps(dic)
    return resp



@app.route('/doctordata/<docid>',methods=['POST', 'GET'])
def doctordata(docid): 
    doctorDetails = mongo.db.doctor.find_one({"id":int(docid)})
    print(doctorDetails)
    print(docid) 
    Name = doctorDetails["name"]
    email = doctorDetails["email"]
    Experience = doctorDetails["years_of_experience"]
    Type = doctorDetails["practice_type"]
    location = doctorDetails["location"]
    dic = {"Name":Name,"email":email,"location":location,"Type":Type,"YoE":Experience}
    resp = dumps(dic)
    return resp

    


@app.route('/prescribe',methods=['POST'])
def prescribe():
    form = request.form
    prescription = form["prescription"]
    reports = form["reports"]
    date = form["date"]
    doctorId = form["docid"]
    patientId = form["id"]
    record = {"doctorId":doctorId, "reports":reports,"prescription":prescription, "date":date}
    record_doctor = {"patientId":patientId,"reports":reports, "prescription":prescription, "date":date}
    mongo.db.patient_prescription.insert({"doctor_id":doctorId,"patient_id":patientId,"prescription":prescription,"date":date})
    print(record)
    print(record_doctor)
    return 'Prescription sent successfully'



@app.route('/prescriptions',methods=['POST'])
def prescriptions():
    form = request.form
    patientId = form["id"]
    result=[]
    a=mongo.db.patient_prescription.find({"patient_id":patientId},{"_id":0})
    for d in a:
        result.append(d) 
    print("1")
    print(result)
    return jsonify({"result":result})  



@app.route('/viewprescription/<id>',methods=['GET'])
def viewprescription(id):
    form = request.form
    print(id)
    presc = {"Medicines":["1","2","3","4"],"Reports":[{"Report_Name":"Sugar","Filename":"438.pdf"},
                                                        {"Report_Name":"BP","Filename":"438.pdf"}]}
    resp = dumps(presc)
    print("Here are the prescriptions")
    return resp




@app.route('/active',methods=['GET','POST'])
def active():
    json = request.json
    doctorId=json["id"]
    doctorDetails = mongo.db.doctor.find_one({"id":doctorId}) 
    activePatients = doctorDetails["active"] 
    send = activePatients
    resp = dumps(send)
    return resp




@app.route('/treated/<id>',methods=['GET','POST']) 
def treated(id):
    json = request.json
    send = {"treated":[{"patientId":"100","Name":"headache"},
                    {"patientId":"101","Name":"headache"}]}
    resp = dumps(send)
    return resp




@app.route('/patientdata',methods=['POST']) 
def patientdata():
    json = request.json
    patientId=json["id"]
    patient = mongo.db.patients.find_one({"id":patientId},{"password":0}) 
    dic = {"patientData" : patient}
    print(dic)
    resp = dumps(dic)
    return resp




@app.route('/requestappointment', methods=['POST','GET']) 
def requestAppointment():
    form = request.form
    patientId = form["id"]
    time = form["time"]
    date = form["date"]
    doctorId = form["doctor"]
    illness = form["illness"]
    patientInfo = {"patientid":patientId, "illness":illness, "time":time, "date":date}
    mongo.db.doctor.update({"id":doctorId}, {"$push":{"requests":patientInfo}})
    print("Your appointment request is sent. You will get a notification once doctor accepts!")
    return 'done'




@app.route('/viewRequests/<id>', methods=['GET']) 
def viewRequests(id):
    req={"requests":[{"patientId":"100","illness":"headache","time":"10 am","data":"17 May"},
                    {"patientId":"100","illness":"headache","time":"10 am","data":"17 May"}]}
    resp = dumps(req)
    return resp




@app.route('/viewappointments/<id>', methods=['GET']) 
def viewappointments(id):
    json = request.json
    req={"appointments":[{"patientId":"100","Name":"headache"},
                    {"patientId":"101","Name":"headache"}]}
    resp = dumps(req)
    return resp




@app.route('/acceptReject', methods=['POST']) 
def acceptReject():
    json = request.json
    value = json["accept"]
    patientId = json["id"]
    doctorId = json["docid"]
	
	
if __name__ == "__main__":
    app.run()
