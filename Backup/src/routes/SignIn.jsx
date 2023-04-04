import axios from "axios";
import React from "react";

class SignIn extends React.Component {
  constructor() {
    super();
    this.state = {
      input: {},
      errors: {},
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    let input = this.state.input;
    input[event.target.name] = event.target.value;

    this.setState({
      input,
    });
  }

  handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      username: event.target.email.value,
      password: event.target.password.value,
    };
    //console.log(data.username, data.password)
    axios
      .post("http://10.191.80.109:9090/login", null, {
        params: {
          username: data.username,
          password: data.password,
        },
      })

      .then((response) => {
        console.log("RESPONSE DATA",response.data); // handle successful login
        debugger
        const role = response.headers['role'];
        // const auth = response.headers['Authorization'];

        console.log("ROLE",role);
      })

      .catch((error) => {
        console.error(error); // handle login error
      });
  };
  // handleSubmit(event) {
  //   event.preventDefault();

  //   if(this.validate()){
  //       console.log(this.state);

  //       let input = {};
  //       input["username"] = "";
  //       input["email"] = "";
  //       input["password"] = "";
  //       input["confirm_password"] = "";
  //       this.setState({input:input});

  //       alert('Demo Form is submitted');
  //   }
  // }

  validate() {
    let input = this.state.input;
    let errors = {};
    let isValid = true;

    // if (!input["username"]) {
    //   isValid = false;
    //   errors["username"] = "Please enter your username.";
    // }

    // if (typeof input["username"] !== "undefined") {
    //   const re = /^\S*$/;
    //   if(input["username"].length < 6 || !re.test(input["username"])){
    //       isValid = false;
    //       errors["username"] = "Please enter valid username.";
    //   }
    // }

    if (!input["email"]) {
      isValid = false;
      errors["email"] = "Please en  ter your email Address.";
    }

    if (typeof input["email"] !== "undefined") {
      var pattern = new RegExp(
        /^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i
      );
      if (!pattern.test(input["email"])) {
        isValid = false;
        errors["email"] = "Please enter valid email address.";
      }
    }

    // if (typeof input["password"] !== "undefined") {
    //   var pattern = new RegExp(
    //     /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/
    //   );
    //   if (!pattern.test(input["password"])) {
    //     isValid = false;
    //     errors["password"] = "Please enter valid password .";
    //   }
    // }

    // if (!input["password"]) {
    //   isValid = false;
    //   errors["password"] = "Please enter your password.";
    // }

    if (!input["confirm_password"]) {
      isValid = false;
      errors["confirm_password"] = "Please enter your confirm password.";
    }

    // if (typeof input["password"] !== "undefined") {
    //   var pattern = new RegExp(
    //     /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/
    //   );
    //   if (input["password"].length < 8) {
    //     isValid = false;
    //     errors["password"] = "Please add at least 8 charachter.";
    //   }
    // }

    // if (
    //   typeof input["password"] !== "undefined" &&
    //   typeof input["confirm_password"] !== "undefined"
    // ) {
    //   if (input["password"] != input["confirm_password"]) {
    //     isValid = false;
    //     errors["password"] = "Passwords don't match.";
    //   }
    // }

    this.setState({
      errors: errors,
    });

    return isValid;
  }

  render() {
    return (
      <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={(e) => this.handleSubmit(e)}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Sign In</h3>
            <div class="form-group mt-3">
              <label for="email">Email Address:</label>
              <input
                type="text"
                name="email"
                value={this.state.input.email}
                onChange={this.handleChange}
                className="form-control mt-1"
                placeholder="Enter email"
                id="email"
              />

              <div className="text-danger">{this.state.errors.email}</div>
            </div>

            <div class="form-group mt-3">
              <label for="password">Password:</label>
              <input
                type="password"
                name="password"
                value={this.state.input.password}
                onChange={this.handleChange}
                className="form-control mt-1"
                placeholder="Enter password"
                id="password"
              />
              <div className="text-danger">{this.state.errors.password}</div>
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
}

export default SignIn;
