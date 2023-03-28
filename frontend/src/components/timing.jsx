import "../components/timing.css"

function DateSelection() {

  
  
    return(
        <div>
      

        <div class="container">
          <h2>Select Shift Timing</h2>
          <label for="dayWeekSelect">Select:</label>
          <select id="dayWeekSelect" name="dayWeek">
            <option value="day">9-6</option>
            <option value="week">10-5</option>
            <option value="week">Week</option>
            <option value="week">Week</option>
          </select>
          
          <br/>
          <button type="submit">Submit</button>
        </div>
        <br/>
        <div class="container">
          <h2>Select type of Request</h2>
          <label for="dayWeekSelect">Select:</label>
          <select id="dayWeekSelect" name="dayWeek">
            <option value="day">Daily</option>
            <option value="week">Weekly</option>
          </select>
          
          <br/>
          <button type="submit">Submit</button>
        </div>
        </div>
    )
}
export default DateSelection;