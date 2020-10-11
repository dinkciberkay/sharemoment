import React from 'react';
import '../App.css';
import RegisterPage from "../components/RegisterPage";
import LoginPage from "../components/LoginPage";
import HomePage from "../components/HomePage";
import UserPage from "../components/UserPage";
import {HashRouter as Router, Redirect, Route, Switch} from 'react-router-dom'
import TopBar from "../components/TopBar";

function App() {
    return (
        <div>
            <Router>
                <TopBar/>
                {/*BrowserRouter Backend sorgularını tetiklediği için backend e de implementasyon yapmamız gerekir. Hash router suan için daha kullanışlı.*/}
                <Switch>
                    <Route exact path="/" component={HomePage}/>
                    <Route exact path="/login" component={LoginPage}/>
                    <Route exact path="/register" component={RegisterPage}/>
                    <Route exact path="/user/:username" component={UserPage}/>
                    <Redirect to="/"/>
                </Switch>
            </Router>
        </div>
    );
}

export default App;
