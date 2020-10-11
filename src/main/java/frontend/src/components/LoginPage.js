import React, {Component} from "react";
import Input from "./Input";
import {Button} from "primereact/button";
import {userLogin} from '../service/LoginService';
import axios from 'axios'
import ButtonWithProgress from "./ButtonWithProgress";
import {withApiProgress} from "../shared/ApiProgress";

class LoginPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userName: null,
            password: null,
            error: null
        }
    }

    onChangeTextArea = (event) => {
        const {name, value} = event.target;
        this.setState({
            [name]: value,
            error: null
        })
    };

    login = async event => {
        //Form içerisinde butona tıklama olayı submit eventi ile gerçekleşiyor.
        //Browserda bizim yerimize bir şeyleri submit etme olayını tetikliyor. Bunu durdurmak için preventDefault kullandık.
        event.preventDefault();
        const {userName, password} = this.state;
        const creds = {
            userName, password
        };
        this.setState({error: null}); // Login talebi devam ederken unauthorized yazmasını engellemek.
        try {
            await userLogin(creds);
        } catch (apiError) { //bu error objesini axios üretiyor.
            this.setState({
                error: apiError.response.data.message
            })
        }
    };

    render() {

        const {userName, password, error} = this.state;
        //
        const {pendingApiCall} = this.props;
        const buttonEnabled = userName && password;

        return (
            <div className="container">
                <form>
                    <br/>
                    <h1 className="text-center">Login</h1>
                    <div className="text-left">
                        <label>Username</label>
                        <Input name="userName"
                               value={userName}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-left">
                        <label>Password</label>
                        <Input name="password"
                               value={password}
                               type="password"
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    {error && <div className="alert alert-danger">{error}</div>}
                    <ButtonWithProgress text="Login" onClick={this.login}
                                        disabled={!buttonEnabled || pendingApiCall}
                                        pendingApiCall={pendingApiCall}/>
                </form>
            </div>
        )
    }


}

const LoginPageWithApiProgress = withApiProgress(LoginPage, "/api/auth");

export default LoginPageWithApiProgress;