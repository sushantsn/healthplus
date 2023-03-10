import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import AddDoctor from "./components/doctor/AddDoctor";
import DoctorList from "./components/doctor/DoctorList";
import Navbar from "./components/Navbar";
import UpdateDoctor from "./components/doctor/UpdateDoctor";
import AddPatient from "./components/patient/AddPatient"; // Import the AddPatient component
import PatientList from "./components/patient/PatientList"; // Import the PatientList component
import UpdatePatient from "./components/patient/UpdatePatient"; // Import the UpdatePatient component



function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route index element={<DoctorList />} />
          <Route path="/" element={<DoctorList />} />
          <Route path="/doctorList" element={<DoctorList />} />
          <Route path="/addDoctor" element={<AddDoctor />} />
          <Route path="/editDoctor/:id" element={<UpdateDoctor />} />
          <Route index element={<PatientList />} />
          <Route path="/patientList" element={<PatientList />} />
          <Route path="/addPatient" element={<AddPatient />} />
          <Route path="/editPatient/:id" element={<UpdatePatient />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
