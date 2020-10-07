import React from 'react';
import './App.css';
import RegisterPage from "./components/RegisterPage";
import LoginPage from "./components/LoginPage";
import ApiProgress from "./shared/ApiProgress";

function App() {
    return (
        <div className="App">
            <ApiProgress>
                {/*Artık ApiProgress içerisinde Child componenti render edip, ona parametre verebiliriz. "this.props.children" */}
                {/*<LoginPage/>*/}
                <RegisterPage/>
            </ApiProgress>
        </div>
    );
}

export default App;
