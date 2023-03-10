import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DoctorService from "../../services/DoctorService";
import Doctor from "./Doctor";

const DoctorList = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [doctors, setDoctors] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await DoctorService.getDoctors();
        setDoctors(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  const deleteDoctor = (e, id) => {
    e.preventDefault();
    DoctorService.deleteDoctor(id).then((res) => {
      if (doctors) {
        setDoctors((prevElement) => {
          return prevElement.filter((doctor) => doctor.id !== id);
        });
      }
    });
  };

  return (
    <div className="container mx-auto my-8">
      <div className="h-12">
        <button
          onClick={() => navigate("/addDoctor")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Add Doctor
        </button>
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                First Name
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Last Name
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Email ID
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Gender
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Telephone Number
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Date Of Birth
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Practicing Since
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Location
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Address
              </th>
              <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Description
              </th>
            </tr>
          </thead>
          {!loading && (
            <tbody className="bg-white">
              {doctors.map((doctor) => (
                <Doctor
                  doctor={doctor}
                  deleteDoctor={deleteDoctor}
                  key={doctor.id}></Doctor>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default DoctorList;
