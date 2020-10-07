import React, {Component} from 'react';
import axios from "axios";

class ApiProgress extends Component {
    state = {
        pendingApiCall: false
    };

    componentDidMount() {
        axios.interceptors.request.use((request) => {
            if (request.url === this.props.path) {
                //Buradaki işlem, biri request attığında aynı anda login ya da registerdan diğerinin de requesti atmasını engellemek
                this.setState({pendingApiCall: true});
            }
            return request;
        });
        axios.interceptors.response.use((response) => {
            if (response.config.url === this.props.path) {
                //Buradaki işlem, biri request attığında birinin sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
                this.setState({pendingApiCall: false});
            }
            return response;
        }, (error) => {
            //Buradaki işlem, biri request attığında birinin hata sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
            if (error.config.url === this.props.path) {
                this.setState({pendingApiCall: false});
            }
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