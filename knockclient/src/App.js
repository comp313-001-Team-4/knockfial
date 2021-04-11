import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Message from "./components/Layout/Message"
import Home from "./components/Home";
import ServiceDetails from "./components/Services/ServiceDetails";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import { Provider } from "react-redux";
import store from "./store";
import Technicians from "./components/Services/Technicians"
import Users from "./components/Services/Users"

import Chat from "./components/Services/Chat";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import UserDetails from "./components/UserManagement/UserDetails";
import TechDetails from "./components/UserManagement/TechDetails";
import Orders from "./components/Services/Orders";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            {
              //Public Routes
            }

            <Route exact path="/" component={Home} />
            <Route exact path="/landing" component={Landing} />

            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/chat" component={Chat} />
            <Route exact path="/chat/:name" component={Chat} />

            {
              //Private Routes
            }
            <Switch>
              <SecuredRoute exact path="/dashboard" component={Dashboard} />
              <SecuredRoute exact path="/dashboard/:id" component={Dashboard} />
              <SecuredRoute exact path="/technicians" component={Technicians} />
              <SecuredRoute exact path="/users" component={Users} />
              <SecuredRoute exact path="/orders" component={Orders} />
              <SecuredRoute exact path="/message" component={Message} />

              <SecuredRoute exact path="/addDetails" component={UserDetails} />
              <SecuredRoute exact path="/addTechDetails" component={TechDetails} />
              <SecuredRoute exact path="/servicedetails/:name" component={ServiceDetails} />

            </Switch>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
