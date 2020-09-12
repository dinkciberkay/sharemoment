import React, {Component} from "react";
import Input from "./Input";
import {Button} from "primereact/button";
import {userLogin} from '../service/LoginService';

class LoginPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userName: null,
            password: null
        }
    }

    onChangeTextArea = (event) => {
        const {name, value} = event.target;
        this.setState({
            [name]: value
        })
    };

    login = (event) => {
        //Form içerisinde butona tıklama olayı submit eventi ile gerçekleşiyor.
        //Browserda bizim yerimize bir şeyleri submit etme olayını tetikliyor. Bunu durdurmak için preventDefault kullandık.
        event.preventDefault();
        const {userName, password} = this.state;
        const creds = {
            userName, password
        };
        userLogin(creds);
    };

    render() {
        return (
            <div className="container">
                <form>
                    <br/>
                    <h1 className="text-center">Login</h1>
                    <div className="text-left">
                        <label>Username</label>
                        <Input name="userName"
                               value={this.state.userName}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-left">
                        <label>Password</label>
                        <Input name="password"
                               value={this.state.password}
                               type="password"
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-center">
                        <Button
                            className="btn btn-primary"
                            label="Login" onClick={this.login}>
                        </Button>
                        <br/>
                    </div>
                </form>
            </div>
        )
    }


}

export default LoginPage;