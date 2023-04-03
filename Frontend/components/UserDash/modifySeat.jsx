import React, { useState } from 'react';
import '../CSS/modifySeat.css';



const SeatModify = () => {
  const [lists, setLists] = useState(['List 1', 'List 2', 'List 3', 'List 4', 'List 5', 'List 6', 'List 7']);

  const handleModify = (index) => {
    const newList = [...lists];
    newList[index] = prompt('Enter new list name:', newList[index]);
    setLists(newList);
  };

  const handleCancel = (index) => {
    const newList = [...lists];
    newList.splice(index, 1);
    setLists(newList);
  };


  return (
    <div className="list-ui-container">
      {lists.map((list, index) => (
        <div className="list-item" key={index}>
          <span>{list}</span>
          <div className="button-group">
            <button onClick={() => handleModify(index)}>Modify</button>
            <button onClick={() => handleCancel(index)}>Cancel</button>
            <button>Change</button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default SeatModify;
