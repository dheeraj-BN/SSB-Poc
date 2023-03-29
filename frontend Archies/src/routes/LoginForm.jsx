import React, { useState, useEffect } from "react";
import axios from "axios";

function LoginForm() {
  const [loginForm, setLoginForm] = useState("");

  useEffect(() => {
    axios
      .get("http://10.191.80.98:9090/login")
      .then((response) => {
        const loginForm = response.data;
        setLoginForm(loginForm);
      })
      .catch((error) => {
        console.error("Error fetching login page:", error);
      });
  }, []);

  return <div dangerouslySetInnerHTML={{ __html: loginForm }}></div>;
}

export default LoginForm;
