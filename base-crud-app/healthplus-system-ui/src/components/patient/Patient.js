// import React from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


const Patient = ({ patient, deletePatient }) => {
  const navigate = useNavigate();
  const editPatient = (e, id) => {
    e.preventDefault();
    navigate(`/editPatient/${id}`);
  };

  return (
    <tr key={patient.id}>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm font-medium text-gray-900">{patient.firstName}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm font-medium text-gray-900">{patient.lastName}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{patient.emailId}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{new Date(patient.dateOfBirth).toLocaleDateString()}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{patient.aadhaar}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{patient.telephoneNumber}</div>
      </td>
      <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
  <Button variant="primary"
    onClick={(e, id) => editPatient(e, patient.id)}
    className="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded mr-2"
  >
    Edit
  </Button>
  <Button variant="danger"
    onClick={(e, id) => deletePatient(e, patient.id)}
    className="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
  >
    Delete
  </Button>
</td>
    </tr>
  );
};

export default Patient;
