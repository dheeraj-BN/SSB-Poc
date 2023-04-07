import { useEffect, useState } from 'react';

function ResetPassword() {
  const [input, setInput] = useState({});
  const [errors, setErrors] = useState({});
  const [status, setstatus] = useState('')
  const [token, setToken] = useState(window.localStorage.getItem("token"))

  function changePassword(){
    fetch(`http://40.88.23.186:9090/api/new/password/19?newPassword=${input}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
          } 
        return response.json();
      })
      .then((data) => {
        // setShifts(newShifts);
        // console.log(data)
        setstatus(data)
        console.log(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });

      window.location = "/login"
  }

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    setInput((prevInput) => ({ ...prevInput, [name]: value }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (validate()) {
      console.log(input);

      setInput({
        password: '',
        confirm_password: '',
      });

      alert('Password Updated');
    }
  };

  const validate = () => {
    let errors = {};
    let isValid = true;

    if (!input.password) {
      isValid = false;
      errors.password = 'Please enter your password.';
    } else if (input.password.length < 6) {
      isValid = false;
      errors.password = 'Please add at least 6 characters.';
    } else {
      const pattern = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
      if (!pattern.test(input.password)) {
        isValid = false;
        errors.password = 'Please enter a valid password.';
      }
    }

    if (!input.confirm_password) {
      isValid = false;
      errors.confirm_password = 'Please enter your confirm password.';
    } else if (input.password !== input.confirm_password) {
      isValid = false;
      errors.password = "Passwords don't match.";
    }

    setErrors(errors);

    return isValid;
  };

  return (
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={handleSubmit}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Password</h3>
          <div className="form-group mt-3">
            <label>New Password</label>
            <input
              type="password"
              name="password"
              value={input.password}
              onChange={handleChange}
              className="form-control mt-1"
              placeholder="Enter password"
              id="password"
            />
            <div className="text-danger">{errors.password}</div>
          </div>
          <div className="form-group mt-3">
            <label>Re-Enter Password</label>
            <input
              type="password"
              name="confirm_password"
              value={input.confirm_password}
              onChange={handleChange}
              className="form-control mt-1"
              placeholder="Enter password"
              id="confirm_password"
            />
            <div className="text-danger">{errors.confirm_password}</div>
          </div>
          <div className="d-grid gap-2 mt-3">
            <button type="submit" onClick={changePassword} className="btn btn-primary">
              Submit
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default ResetPassword;
