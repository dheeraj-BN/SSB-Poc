import React, { useState, useEffect } from "react";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState();
  const [formSubmitted, setFormSubmitted] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://10.191.80.98:9090/login", {
        username,
        password,
      });
      console.log(response.data); // handle successful login
      setRole(response.data.roles);
      setFormSubmitted(true);
    } catch (error) {
      console.error(error); // handle error
    }
  };

  useEffect(() => {
    if (formSubmitted) {
      if (role === "ROLE_ADMIN") {
        window.location = "/admin";
      } else {
        window.location = "/";
      }
    }
  }, [ role]);

  return (
    <form onSubmit={handleSubmit}>
      <label>
                Username:
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </label>
      <br />
      <label>
                Password:
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </label>
      <br />
      <button type="submit">Log In</button>
    </form>
  );
}

export default Login;
