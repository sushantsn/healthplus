// import React from "react";

// const Navbar = () => {
//   return (
//     <div className="bg-gray-800">
//       <div className="h-16 px-8 flex items-center">
//         <p className="text-white font-bold">HealthPlus</p>
//       </div>
//     </div>
//   );
// };

// export default Navbar;
import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="bg-gray-800">
      <div className="container mx-auto h-16 px-8 flex items-center justify-between">
        <p className="text-white font-bold">HEALTHPLUS</p>
        <ul className="flex items-center">
          <li className="mr-6">
            <Link to="/doctorList" className="text-gray-300 hover:text-white">
              Doctors
            </Link>
          </li>
          <li className="mr-6">
            <Link to="/patientList" className="text-gray-300 hover:text-white">
              Patients
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
