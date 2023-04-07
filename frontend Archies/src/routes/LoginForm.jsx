import React, { useState, useEffect} from "react";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState();
  const [formSubmitted, setFormSubmitted] = useState(false);
  const [token, setToken] = useState();
  const [id,setid]=useState();
  const [error, setError] = useState({})
  const [status , setstatus] = useState('')



  const handleSubmit = async (e) => {


    // const handleSignout = ()=>{
    //   setuser(null)
    //   localStorage.removeItem("token")
    // }


    e.preventDefault();
    try {
      const response = await axios.post("http://10.191.80.98:9090/login", {
        username,
        password,
      });
      console.log(response.data); // handle successful login
      setRole(response.data.roles);
      setToken(response.data.token);
      setid(response.data.id)
      setstatus(response.data.status)
      setFormSubmitted(true);
    } catch (error) {
      console.error(error); // handle error
    }
  };

  useEffect(() => {
    if(status){
      if (formSubmitted) {
        if (role === "ROLE_ADMIN") {
          window.localStorage.setItem("token", token);
          window.location = "/admin";
        } 
        else if(role === "ROLE_DEVELOPER") {
          window.location = "/developer";
          window.localStorage.setItem('token', token)
        }
        else{
          window.location = "/";
          window.localStorage.setItem('token', token)
        }
      }
    }
    else{
        // window.location = '/resetpass' 
    }
  }, [role,status]);
  localStorage.setItem("userId",id);

  return (
    <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={ handleSubmit}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Sign In</h3>
            <div class="form-group mt-3">
              <label type = "text" for="email">Email Address:</label>
              <input
                type="text"
                name="email"
                value={username}
                onChange={(e)=>setUsername(e.target.value)}
                className="form-control mt-1"
                placeholder="Enter email"
                id="email"
              />

              <div className="text-danger">{error.email}</div>
            </div>

            <div class="form-group mt-3">
              <label for="password">Password:</label>
              <input
                type="password"
                name="password"
                value={password}
                onChange={(e)=>setPassword(e.target.value)}
                className="form-control mt-1"
                placeholder="Enter password"
                id="password"
              />
              <div className="text-danger">{error.password}</div>
            </div>
            <div className="d-grid gap-2 mt-3">
              <button type="submit" className="btn btn-primary">
                Submit
              </button>
            </div>
            <p className="forgot-password text-right mt-2">
              <a href={"/email"}>Forgot password?</a>
            </p>
          </div>
        </form>
      </div>
  );
}

export default Login;
