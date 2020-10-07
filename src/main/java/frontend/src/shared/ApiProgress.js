import React, {Component} from 'react';
import axios from "axios";

class ApiProgress extends Component {
    state = {
        pendingApiCall: false
    };

    componentDidMount() {
        axios.interceptors.request.use((request) => {
            this.setState({pendingApiCall: true});
            return request;
        });
        axios.interceptors.response.use((response) => {
            this.setState({pendingApiCall: false});
            return response;
        }, (error) => {
            this.setState({pendingApiCall: false});
            throw error;
        })
    }

    render() {
        const {pendingApiCall} = this.state;
        return (
            <div>
                {/*{this.props.children} /!*Login veya Register Page ler App.js içerisinde ApiProgress Componentinin children durumunda, burada da onları çağırmış oluruz.*!/*/}

                {/*ApiProgressteki değeri Child Componentimiz olan LoginPage e passlayabiliriz.*/}
                {React.cloneElement(this.props.children, {pendingApiCall})}
                {/*Bu sayede artık LoginPage içerisinde pendingApiCall diye bir parametre geliyor*/}
            </div>
        );
    }
}

export default ApiProgress;