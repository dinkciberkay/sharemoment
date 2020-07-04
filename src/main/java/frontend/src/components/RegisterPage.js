import React, {Component} from "react";
import {InputText} from "primereact/inputtext";
import {Button} from "primereact/button";
import {userRegister} from '../service/RegisterService'


class RegisterPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userName: null,
            displayName: null,
            password: null,
            passwordRepeat: null,
            pendingApiCall: false
        }
    }

    onChangeTextArea = (event) => {
        const {name, value} = event.target;
        this.setState({[name]: value})
    };

    register = async event => {
        event.preventDefault();

        const {userName, displayName, password} = this.state;
        // const body = {
        //     userName: userName,
        //     displayName: displayName,
        //     password: password
        // };

        this.setState({pendingApiCall: true});
        // İsimlendirmeler aynı olduğu zaman bu şekilde kullanılabilir
        const body = {
            userName,
            displayName,
            password
        };

        try {
            const response = await userRegister(body);
        } catch (e) {

        }

        this.setState({pendingApiCall: false})

    };

    render() {
        const {pendingApiCall} = this.state;
        return (
            <div className="container">
                <br/>
                <form>
                    <h1 className="text-center">Sign Up</h1>
                    <div className="text-left">
                        <label>Username</label>
                        <InputText
                            className="form-control"
                            name="userName"
                            value={this.state.userName}
                            onChange={this.onChangeTextArea}/>
                    </div>
                    <div className="text-left">
                        <label>Display Name</label>
                        <InputText
                            className="form-control"
                            label="Display Name" name="displayName"
                            value={this.state.displayName}
                            onChange={this.onChangeTextArea}/>
                    </div>
                    <div className="text-left">
                        <label>Password</label>
                        <InputText
                            className="form-control"
                            name="password"
                            value={this.state.password}
                            onChange={this.onChangeTextArea}/>
                    </div>
                    <div className="text-left">
                        <label>Password Repeat</label>
                        <InputText
                            className="form-control"
                            name="passwordRepeat"
                            value={this.state.passwordRepeat}
                            onChange={this.onChangeTextArea}/>
                    </div>
                    <br/>
                    <div className="text-center">
                        <Button
                            className="btn btn-primary"
                            label="Register" onClick={this.register}
                            disabled={pendingApiCall}>
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