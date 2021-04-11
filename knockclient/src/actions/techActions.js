import axios from "axios";
import { GET_ERRORS, GET_ORDERS, GET_TECH ,GET_TECHS,GET_TECHSS} from "./types";

export const getTechs = (name) => async dispatch => {
    try {
    const res = await axios.get(`http://localhost:8080/api/users/techdetails/${name}`);
    dispatch({
      type: GET_TECH,
      payload: res.data
    });
} catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};
export const getTechss = () => async dispatch => {
  try {
  const res = await axios.get(`http://localhost:8080/api/users/technicians`);
  console.log(res.data);
  dispatch({
    type: GET_TECHS,
    payload: res.data
  });
} catch (err) {
  dispatch({
    type: GET_ERRORS,
    payload: err.response.data
  });
}
};
export const getTechsss = () => async dispatch => {
  try {
  const res = await axios.get(`http://localhost:8080/api/users/users`);
  console.log(res.data);
  dispatch({
    type: GET_TECHSS,
    payload: res.data
  });
} catch (err) {
  dispatch({
    type: GET_ERRORS,
    payload: err.response.data
  });
}
};

export const getOrders = () => async dispatch => {
  try {
  const res = await axios.get(`http://localhost:8080/api/users/orders`);
  console.log(res.data);
  dispatch({
    type: GET_ORDERS,
    payload: res.data
  });
} catch (err) {
  dispatch({
    type: GET_ERRORS,
    payload: err.response.data
  });
}
};