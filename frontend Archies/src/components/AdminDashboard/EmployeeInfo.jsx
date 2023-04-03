import React from 'react';
import Sidebar1 from './Sidebar';
import EmployeeData from '../EmployeeData';
import "../../css/adminDashboard/employee.css";


function EmployeeInfo(props) {
    return (
        <div className='empdata'>
            <div className='item1'>
                <Sidebar1/>
            </div>
            <div className='empscroller item2'>
                <EmployeeData/>
            </div>
        </div>
    );
}

export default EmployeeInfo;