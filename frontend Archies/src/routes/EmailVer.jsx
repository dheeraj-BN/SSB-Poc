
import React from "react"
// import { Link } from "react-router-dom";



class EmailVer extends React.Component {
    constructor() {
        super();
        this.state = {
          input: {},
          errors: {}
        };
         
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
         
      handleChange(event) {
        let input = this.state.input;
        input[event.target.name] = event.target.value;
      
        this.setState({
          input
        });
      }
         
      handleSubmit(event) {
        event.preventDefault();
      
        if(this.validate()){
            console.log(this.state);
      
            let input = {};
            input["email"] = "";
            this.setState({input:input});
      
            alert('email submitted');
        }
      }
      
      validate(){
          let input = this.state.input;
          let errors = {};
          let isValid = true;

          if (!input["email"]) {
            isValid = false;
            errors["email"] = "Please enter your email Address.";
          }
      
          if (typeof input["email"] !== "undefined") {
              
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(input["email"])) {
              isValid = false;
              errors["email"] = "Please enter valid email address.";
            }
          }
          
          this.setState({
            errors: errors
          });
      
          return isValid;
      }
    render(){
        return(
            <div className="Auth-form-container">
                <form className="Auth-form" onSubmit={this.handleSubmit}>
                <div className="Auth-form-content">
                        <h3>Enter your Register Email</h3>
                        <div className="form-group mt-3">
                            <label>Email address</label>
                            <input 
                                type="text" 
                                name="email" 
                                value={this.state.input.email}
                                onChange={this.handleChange}
                                className="form-control mt-1" 
                                placeholder="Enter email" 
                            id="email" />
                            <div className="text-danger">{this.state.errors.email}</div>

                        </div>
                        <div className="d-grid gap-2 mt-3">
                            <button type="submit" className="btn btn-primary">
                                Submit
                            </button>
                        </div>
                </div>
            </form>
            </div>
        )
    }
  }

  export default EmailVer