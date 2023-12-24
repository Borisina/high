Healthcare Management Application: healthcare management system created using Spring Boot and REST services. The application allow healthcare providers to manage patient records, schedule appointments, and prescribe medication. The system also integrates with a third-party service Leaflet to provide directions to healthcare facilities.

1. Registration and Authentication:
- Open the registration link (Assuming '/auth/signup/doctor' for doctors and '/auth/signup/patient' for patients).
- Fill the registration form with your username, password and roles (either Doctor or Patient).
- After successful registration, you can log in using the credentials you signed up with.

3. Healthcare Facility Management:
- Visit /facilities to see all the available facilities on the map. Map also provides routes to the facilities.

4. Patient Management:
- Visit /patients/ for handling patient operations. It supports the following operations:
- GET /patients/: Returns a list of all patients.
- PUT /patients/{id}: Updates the information of the patient with the given id.
- DELETE /patients/{id}: Deletes the patient with the given id.
- GET /patients/{id}: Returns the information of the patient with the given id.

5. Prescription Management:
- Visit /prescriptions for handling prescription operations. It supports the following operations:
- POST /prescriptions/doctor/create/{patientId}: Allows the doctor to create a prescription for a patient (requires the doctor to be authenticated).
- GET /prescriptions/doctor: Lists all prescriptions made by the authenticated doctor. A doctor can also filter prescriptions by patient id.
- GET /prescriptions/patient: Lists all prescriptions for the authenticated patient.

6. Appointment Management:
- Visit /appointments for handling appointment operations. It supports the following operations:
- POST /appointments/doctor-{doctorId}/patient-{patientId}: Creates a new appointment for a specific doctor with a specific patient. The doctor and patient Ids are part of the URL. The appointment details (like date and time) should be sent as JSON in the body of the request.
- GET /appointments/doctor/{id}: Lists all appointments for the doctor with the given id.
- GET /appointments/patient/{id}: Lists all appointments for the patient with the given id.
- In the appointment creation (POST /appointments/doctor-{doctorId}/patient-{patientId}), you need to provide the appointment's information (like the date and time) in the request body as a JSON. If the appointment time already exists for the doctor or patient, an error will be returned with a status of HttpStatus.CONFLICT. If the appointment is successfully created, a success message will be returned with a status of HttpStatus.CREATED.


Feedback:
1. Was it easy to complete the task using AI?
- AI is a pretty good helper, but sometimes AI didn't meet my expectations. Maybe I was giving bad prompts.
2. How long did task take you to complete?
- I spent 10 hours to complete this task.
3. Was the code ready to run after generation? What did you have to change to make it usable?
- The controller part was almost completely made by my self, because AI didn't meet my expectations. The other part was pretty well maden by AI.
4. Which challenges did you face during completion of the task?
- I actually didn't really understand, what my application should do and how complex it should be. Also frontend part with integrated map was not easy.
5. Which specific prompts you learned as a good practice to complete the task?
- I didn't use any specific prompts. All my prompts were pretty simple.