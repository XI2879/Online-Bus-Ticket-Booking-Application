import React, { useState, useEffect } from 'react';
 
import { viewBuses } from '../services/ManageService';

const BookingForm = () => {
  const [bookingData, setBookingData] = useState({
    date: '',
    time: '',
    source: '',
    destination: '',
    journeyDate: '',
    bookedSeat: 0,
    fare: 0,
    busId: 0, 
    userId: 0
  });
  const [successMessage, setSuccessMessage] = useState('');
  const [buses, setBuses] = useState([]); 

  useEffect(() => {
    fetchBuses(); 
  }, []);

  const fetchBuses = async () => {
    try {
      const response = await viewBuses(routeFrom,routeTo,busJourneyDate); 
      setBuses(response.data);
    } catch (error) {
      console.error('Error fetching buses:', error);
     
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setBookingData({ ...bookingData, [name]: value });
  };

  const handleBooking = async (e) => {
    e.preventDefault();
    try {
      const response = await bookBus(bookingData); 
      console.log('Booking successful:', response);
      setSuccessMessage('Booking successful!'); 
      
    } catch (error) {
      console.error('Booking failed:', error);
      
    }
  };

  return (
    <div className="booking-form">
      <h2>Book Bus</h2>
      <form onSubmit={handleBooking}>
       
        <div>
          <label htmlFor="date">Date</label>
          <input type="text" id="date" name="date" placeholder="Enter date" onChange={handleInputChange} />
        </div>
        
        <div>
          <label htmlFor="busId">Bus ID</label>
          <select id="busId" name="busId" onChange={handleInputChange}>
            <option value="">Select Bus</option>
            {buses.map((bus) => (
              <option key={bus.busId} value={bus.busId}>{bus.busName}</option>
            ))}
          </select>
        </div>
        <button type="submit">Book</button>
      </form>
      {successMessage && <div className="success-message">{successMessage}</div>} {/* Display success message */}
    </div>
  );
};

export default BookingForm;