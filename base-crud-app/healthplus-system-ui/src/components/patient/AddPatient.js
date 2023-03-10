import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PatientService from "../../services/PatientService";

const AddPatient = () => {
  const [patient, setPatient] = useState({
    id: "",
    firstName: "",
    lastName: "",
    emailId: "",
    aadhaar: "",
    telephoneNumber: "",
    dateOfBirth: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setPatient({ ...patient, [e.target.name]: value });
  };

  const savePatient = (e) => {
    e.preventDefault();
    PatientService.savePatient(patient)
      .then((response) => {
        console.log(response);
        navigate("/patientList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setPatient({
      id: "",
      firstName: "",
      lastName: "",
      emailId: "",
      aadhaar: "",
      telephoneNumber: "",
      dateOfBirth: "",
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Patient</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            First Name
          </label>
          <input
            type="text"
            name="firstName"
            value={patient.firstName}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Last Name
          </label>
          <input
            type="text"
            name="lastName"
            value={patient.lastName}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Email
          </label>
          <input
            type="email"
            name="emailId"
            value={patient.emailId}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Aadhaar
          </label>
          <input
          type="text"
name="aadhaar"
value={patient.aadhaar}
onChange={(e) => handleChange(e)}
className="h-10 w-96 border mt-2 px-2 py-2"
/>
</div>
<div className="items-center justify-center h-14 w-full my-4">
<label className="block text-gray-600 text-sm font-normal">
Telephone Number
</label>
<input
type="text"
name="telephoneNumber"
value={patient.telephoneNumber}
onChange={(e) => handleChange(e)}
className="h-10 w-96 border mt-2 px-2 py-2"
/>
</div>
<div className="items-center justify-center h-14 w-full my-4">
<label className="block text-gray-600 text-sm font-normal">
Date of Birth
</label>
<input
type="date"
name="dateOfBirth"
value={patient.dateOfBirth}
onChange={(e) => handleChange(e)}
className="h-10 w-96 border mt-2 px-2 py-2"
/>
</div>
<div className="flex justify-end">
<button
type="submit"
onClick={(e) => savePatient(e)}
className="text-white py-2 px-4 rounded-md bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
>
Save
</button>
<button
type="button"
onClick={(e) => reset(e)}
className="ml-3 text-white py-2 px-4 rounded-md bg-gray-500 hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-500 focus:ring-offset-2"
>
Reset
</button>
</div>
</div>
</div>
);
};

export default AddPatient;  