import React from 'react';
import logo from '../assets/sharemoment.png'
import {Link} from 'react-router-dom';

const TopBar = () => {
    return (
        <div className="shadow-sm bg-light mb-3">

            <nav className="navbar navbar-expand-lg navbar-light container navbar-expand">
                {/*Link router ın içerisinde olmalı. App içerisinde Router var onun altında çağırdığımız için sorun olmadı.*/}
                <Link className="navbar-brand" to="/">
                    <img src={logo} width='100' alt="Share Moment Logo"/>
                    Share Moment
                </Link>

                <ul className="navbar-nav ml-auto"> {/*sağ tarafa yerleştirdik.*/}
                    <li>
                        <Link className="nav-link" to="/register"> Register </Link>
                    </li>
                    <li>
                        <Link className="nav-link" to="/login"> Login </Link>
                    </li>
                </ul>

            </nav>
        </div>
    );
};

export default TopBar;