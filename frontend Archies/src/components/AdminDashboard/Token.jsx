import React, { useState } from 'react';
import "../../css/adminDashboard/token.css";

function Token() {
  const [user, setUser] = useState({
    name: 'John Doe',
    email: 'johndoe@example.com',
    phone: '123-456-7890',
  });

  const [isEditMode, setIsEditMode] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({ ...prevUser, [name]: value }));
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    // Handle update logic
    setIsEditMode(false);
  };

  const handleEdit = () => {
    setIsEditMode(true);
  };

  const handleCancel = () => {
    setIsEditMode(false);
  };

  return (

   <div>
     <form onSubmit={handleUpdate} className='tokenform'>
      <label htmlFor="name">Name:</label>
      <input
        type="text"
        id="name"
        name="name"
        value={user.name}
        onChange={handleChange}
        disabled={!isEditMode}
      />

      <label htmlFor="email">Email:</label>
      <input
        type="email"
        id="email"
        name="email"
        value={user.email}
        onChange={handleChange}
        disabled={!isEditMode}
      />

      <label htmlFor="phone">Phone:</label>
      <input
        type="tel"
        id="phone"
        name="phone"
        value={user.phone}
        onChange={handleChange}
        disabled={!isEditMode}
      />

      {isEditMode ? (
        <div>
          <button type="submit">Save</button>
          <button type="button" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      ) : (
        <button type="button" onClick={handleEdit}>
          Edit
        </button>
      )}
    </form>

    <div>
        <p>{user.name}</p>
        <p>{user.email}</p>
        <p>{user.phone}</p>
    </div>
   </div>
  );
}

export default Token;