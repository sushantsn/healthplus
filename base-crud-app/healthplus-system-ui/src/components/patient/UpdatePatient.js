import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import PatientService from "../../services/PatientService";

const UpdatePatient = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [patient, setPatient] = useState({
    id: "",
    firstName: "",
    lastName: "",
    emailId: "",
    aadhaar: "",
    telephoneNumber: "",
    dateOfBirth: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setPatient({ ...patient, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await PatientService.getPatientById(patient.id);
        setPatient(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const updatePatient = (e) => {
    e.preventDefault();
    console.log(patient);
    PatientService.updatePatient(patient, id)
      .then((response) => {
        navigate("/patientList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Update Patient</h1>
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
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
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
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
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
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
  <label className="block text-gray-600 text-sm font-normal">Aadhaar</label>
  <input
    type="text"
    name="aadhaar"
    value={patient.aadhaar}
    onChange={(e) => handleChange(e)}
    className="h-10 w-96 border mt-2 px-2 py-2"
  />
</div>
<div className="items-center justify-center h-14 w-full my-4">
  <label className="block text-gray-600 text-sm font-normal">Telephone</label>
  <input
    type="text"
    name="telephone"
    value={patient.telephone}
    onChange={(e) => handleChange(e)}
    className="h-10 w-96 border mt-2 px-2 py-2"
  />
</div>

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            onClick={updatePatient}
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6">
            Update
          </button>
          <button
            onClick={() => navigate("/patientList")}
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpdatePatient;
