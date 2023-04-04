import React, { useState } from 'react';
import axios from 'axios';

 

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

 

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://10.191.80.98:9090/login', {
        username,
        password,
      });
      console.log(response.data);
      // handle successful login
    } catch (error) {
      console.error(error);
      // handle error
    }
  };

 

  return (
<form onSubmit={handleSubmit}>
<label>
        Username:
<input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
</label>
<br />
<label>
        Password:
<input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
</label>
<br />
<button type="submit">Log In</button>
</form>
  );
}

 

export default Login;