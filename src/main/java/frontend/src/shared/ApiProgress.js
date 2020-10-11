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
            this.requestInterceptor = axios.interceptors.request.use((request) => { //Buradaki işlem, biri request attığında aynı anda login ya da registerdan diğerinin de requesti atmasını engellemek
                this.listenApiCall(request.url, true);
                return request;
            });

            this.responseInterceptor = axios.interceptors.response.use((response) => {
                //Buradaki işlem, biri request attığında birinin sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
                this.listenApiCall(response.config.url, false);
                return response;
            }, (error) => { //Buradaki işlem, biri request attığında birinin hata sonucu döndüğünde diğerinin sonucunu aniden tamamlaması, isteğini bitirmesine imkan vermemesi sorununu engellemek
                this.listenApiCall(error.config.url, false);
                throw error;
            })
        }

        //Memory leak sorunu ortadan kalktı.
        //Component ekranda değilse onunla alakalı bir şey geride bırakmamış olduk.
        componentWillUnmount() {
            axios.interceptors.request.eject(this.requestInterceptor);
            axios.interceptors.response.eject(this.responseInterceptor);
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