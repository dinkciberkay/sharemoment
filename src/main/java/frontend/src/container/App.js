import React from 'react';
import '../App.css';
import ApiProgress from "../shared/ApiProgress";
import RegisterPage from "../components/RegisterPage";
import LoginPage from "../components/LoginPage";

function App() {
    return (
        <div className="row">
            <div className="col">
                <RegisterPage/>
            </div>
            <div className="col">
                <LoginPage/>
            </div>
        </div>
    );
}

export default App;
