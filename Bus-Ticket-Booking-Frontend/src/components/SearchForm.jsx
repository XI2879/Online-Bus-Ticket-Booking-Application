import React, { useState } from 'react';
import { viewBuses } from '../services/ManageService';
import { useNavigate } from 'react-router-dom';
import { isUserLoggedIn, logout } from '../services/AuthService';

const SearchForm = () => {
  const [from, setFrom] = useState('');
  const [destination, setDestination] = useState('');
  const [localDate, setLocalDate] = useState('');
  const [buses, setBuses] = useState([]); 
  const navigate = useNavigate();
  
   


  const handleSearch = async () => {
    const response = await viewBuses(from, destination,localDate);
    setBuses(response.data);
    
  };
  const handleBookBus = (busId) => {
    if (isUserLoggedIn()) {
        navigate('/book')
      } else {
        navigate('/login'); // Redirect to login page
      }
  };

  return (
    <div className="search-form" style={{ textAlign: 'center', padding: '20px' }}>
      <div style={{ display: 'inline-flex' }}>
        <input
          type="text"
          className="form-control"
          placeholder="From"
          value={from}
          onChange={(e) => setFrom(e.target.value)}
          style={{ marginRight: '10px', padding: '10px', width: '200px' }}
        />
        <input
          type="text"
          className="form-control"
          placeholder="Destination"
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
          style={{ marginRight: '10px', padding: '10px', width: '200px' }}
        />
        <input
          type="date"
          className="form-control"
          placeholder="Local Date"
          value={localDate}
          onChange={(e) => setLocalDate(e.target.value)}
          style={{ marginRight: '10px', padding: '10px', width: '200px' }}
        />
      </div>
      <button className="btn btn-primary" onClick={handleSearch}>
        Search
      </button>
      
       {/* Render bus details */}
       {buses.map((bus, index) => (
          <div key={index} className="card" style={{ margin: '10px', padding: '10px', minWidth: '300px' }}>
            <h4>{bus.busName}</h4>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <p><strong>Bus Type:</strong> {bus.busType}</p>
              <p><strong>Departure Time:</strong> <span style={{ color: 'blue' }}>{bus.departureTime}</span></p>
              <p><strong>Arrival Time:</strong> <span style={{ color: 'blue' }}>{bus.arrivalTime}</span></p>
            </div>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
              <p><strong>Available Seats:</strong> {bus.availableSeats}</p>
              <p><strong>Route:</strong> {bus.route.routeFrom} to {bus.route.routeTo}</p>
            </div>
            <button className="btn btn-success" onClick={() => handleBookBus(bus.busId)}>Book</button>
          </div>
          
        ))}
      
      {isUserLoggedIn() && (
        <div>
          <button className="logout" onClick={logout}>Logout</button>
        </div>
      )}
      
   </div>
   
 );
};

export default SearchForm;