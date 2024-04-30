import axios from "axios";
import { getToken } from "./AuthService.js";

const BASE_REST_API_URL = "http://localhost:8080/api";

axios.interceptors.request.use(
  function (config) {
    config.headers["Authorization"] = getToken();
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);


export const viewBuses = (routeFrom,routeTo,busJourneyDate) => axios.get(BASE_REST_API_URL + "/routes/buses",{
  params: {
    routeFrom:routeFrom,
    routeTo: routeTo,
    busJourneyDate:busJourneyDate
  }
});

export const bookBus = (bookingData) => axios.post(BASE_REST_API_URL + "/buses/reservation/add",bookingData);




