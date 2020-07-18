import React, {Component} from "react";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {userRegister} from '../service/RegisterService'
import Input from "./Input";


class RegisterPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userName: null,
            displayName: null,
            password: null,
            passwordRepeat: null,
            pendingApiCall: false,
            errors: {}
        }
    }

    onChangeTextArea = (event) => {
        const {name, value} = event.target;
        const errors = {...this.state.errors};
        errors[name] = undefined;
        if (name === 'password' || name === 'passwordRepeat') {
            if (name === 'password' && value !== this.state.passwordRepeat) {
                errors.passwordRepeat = 'Password Mismatch';
            } else if (name === 'passwordRepeat' && value !== this.state.password) {
                errors.passwordRepeat = 'Password Mismatch';
            } else {
                errors.passwordRepeat = undefined;
            }
        }
        this.setState({
            [name]: value,
            errors
        })
    };

    register = async event => {
        event.preventDefault();
        const {userName, displayName, password} = this.state;
        this.setState({pendingApiCall: true});

        const body = {
            userName,
            displayName,
            password
        };

        try {
            const response = await userRegister(body);
        } catch (error) {
            if (error.response.data.validationErrors) {
                this.setState({errors: error.response.data.validationErrors})
            }
        }
        this.setState({pendingApiCall: false})

    };

    render() {
        const {pendingApiCall, errors} = this.state;
        const {userName, displayName, password, passwordRepeat} = errors;
        return (
            <div className="container">
                <br/>
                <form>
                    <h1 className="text-center">Sign Up</h1>
                    {/* Input Component 'ini kendimiz Custom bir component haline getirdik*/}
                    <div className="text-left">
                        <label>Username</label>
                        <Input name="userName"
                               value={this.state.userName}
                               error={userName}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-left">
                        <label>Display Name</label>
                        <Input name="displayName"
                               value={this.state.displayName}
                               error={displayName}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-left">
                        <label>Password</label>
                        <Input name="password"
                               value={this.state.password}
                               error={password}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-left">
                        <label>Password Repeat</label>
                        <Input name="passwordRepeat"
                               value={this.state.passwordRepeat}
                               error={passwordRepeat}
                               onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-center">
                        <Button
                            className="btn btn-primary"
                            label="Register" onClick={this.register}
                            disabled={passwordRepeat !== undefined || pendingApiCall}>
                            {/*{this.state.pendingApiCall && <ProgressSpinner className="p-progress-circle"/>}*/}
                            {pendingApiCall && <span className="spinner-border spinner-border-sm"/>}
                        </Button>
                    </div>
                </form>
            </div>
        )
    }
}

export default RegisterPage;