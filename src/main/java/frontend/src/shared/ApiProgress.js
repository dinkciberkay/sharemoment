import React, {Component} from 'react';
import axios from "axios";

function getDisplayName(WrappedComponent) {
    return WrappedComponent.displayName || WrappedComponent.name || 'Component';
}


export function withApiProgress(WrappedComponent, apiPath) {
    return class extends Component {

        static displayName = `ApiProgress(${getDisplayName(WrappedComponent)})`;
        // static displayName = 'ApiProgress(' + getDisplayName(WrappedComponent) + ')';

        state = {
            pendingApiCall: false
        };

        componentDidMount() {
            axios.interceptors.request.use((request) => { //Buradaki işlem, biri request attığında aynı anda login ya da registerdan diğerinin de requesti atmasını engellemek
                this.listenApiCall(request.url, true);
                return request;
            });
            axios.interceptors.response.use((response) => {
                //Buradaki işlem, biri request attığında birinin sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
                this.listenApiCall(response.config.url, false);
                return response;
            }, (error) => { //Buradaki işlem, biri request attığında birinin hata sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
                this.listenApiCall(error.config.url, false);
                throw error;
            })
        }

        //Buradaki işlem, gelen url'e göre in progress'i setlemek.
        listenApiCall = (url, inProgress) => {
            if (url === apiPath) {
                this.setState({pendingApiCall: inProgress});
            }
        };

        render() {
            const {pendingApiCall} = this.state;
            return <WrappedComponent pendingApiCall={pendingApiCall} {...this.props} />; //Burada {...} spread operatörü ile propertylerimiz passlıyoruz.
            //High Order Komponent ımızın başkalarıyla çalışma ihtimalini düşünerek potansiyel bütün propertyleri sardığımız komponenta passlıyoruz.
        }
    }

}