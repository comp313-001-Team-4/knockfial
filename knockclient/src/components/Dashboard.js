import React, { Component } from "react";
import './Dashboard.css'
class Dashboard extends Component {
 

  render() {
    

    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
                 Dashboard
                 <img className="" src={window.location.origin +'/Icons/display.png'}/>
              <br />
              <hr />
             
            </div>
          </div>
        </div>
      </div>
    );
  }
}


export default  Dashboard;
