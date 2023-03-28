import React, { useState } from 'react';

const WeekdayPicker = () => {
  const [days, setDays] = useState([]);

  const handleDayClick = (day) => {
    const index = days.indexOf(day);
    if (index > -1) {
      setDays([...days.slice(0, index), ...days.slice(index + 1)]);
    } else {
      setDays([...days, day]);
    }
  };

  const weekdays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];

  return (
    <div>
      {weekdays.map((day) => (
        <label key={day}>
          <input
            type="checkbox"
            value={day}
            checked={days.indexOf(day) > -1}
            onChange={() => handleDayClick(day)}
          />
          {day}
        </label>
      ))}
    </div>
  );
};

export default WeekdayPicker;
