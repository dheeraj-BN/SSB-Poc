import React, { useState } from "react";
import {
  CDBSidebar,
  CDBSidebarContent,
  CDBSidebarFooter,
  CDBSidebarHeader,
  CDBSidebarMenu,
  CDBSidebarMenuItem,
} from "cdbreact";
import { Link, NavLink } from "react-router-dom";
import Logout from "../../routes/Logout";


const Sidebar1 = () => {

  const [user, setuser] = useState(null)
 
  return (
    <div style={{ display: "flex", height: "100vh", overflow: "scroll initial" }}>
      <CDBSidebar textColor="#fff" backgroundColor="#333">
        <CDBSidebarHeader prefix={<i className="fa fa-bars fa-large"></i>}>
          <a
            href="/"
            className="text-decoration-none"
            style={{ color: "inherit" }}
          >
            Admin
          </a>
        </CDBSidebarHeader>

        <CDBSidebarContent className="sidebar-content">
          <CDBSidebarMenu>
            <Link  to="/admin" className={(navdata)=>(navdata.active ? "activeClicked" : 'none')}>
              <CDBSidebarMenuItem icon="columns">Dashboard</CDBSidebarMenuItem>
            </Link>
            <Link  to="/qrcodescan" className={(navdata)=>(navdata.active ? "activeClicked" : 'none')}>
              <CDBSidebarMenuItem icon="table"> QR Scan</CDBSidebarMenuItem>
            </Link>
            <Link  to="/empdata" className={(navdata)=>(navdata.active ? "activeClicked" : 'none')}>
              <CDBSidebarMenuItem icon="table"> Data</CDBSidebarMenuItem>
            </Link>
            <Link to="/profileadmin" className={(navdata)=>(navdata.active ? "activeClicked" : 'none')}>
              <CDBSidebarMenuItem icon="user">Profile</CDBSidebarMenuItem>
            </Link>
           
          </CDBSidebarMenu>
          <h3><Logout/></h3>
        </CDBSidebarContent>

        <CDBSidebarFooter style={{ textAlign: "center" }}>
          <div
            style={{
              padding: "20px 5px",
            }}
          >
            Valtech POC
          </div>
        </CDBSidebarFooter>
      </CDBSidebar>
    </div>
  );
};

export default Sidebar1;
