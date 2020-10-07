import React from 'react';
import '../App.css';
import ApiProgress from "../shared/ApiProgress";
import RegisterPage from "../components/RegisterPage";
import LoginPage from "../components/LoginPage";

function App() {
    return (
        <div className="row">
            <div className="col">
                <ApiProgress path="/api/users">
                    {/*Artık ApiProgress içerisinde Child componenti render edip, ona parametre verebiliriz. "this.props.children" */}
                    <RegisterPage/>
                    {/*Login ve Register işlemlerinin karışmaması için parametre geçtik */}
                </ApiProgress>
            </div>
            <div className="col">
                <ApiProgress path="/api/auth">
                    {/*Login ve Register işlemlerinin karışmaması için parametre geçtik */}
                    {/*Artık ApiProgress içerisinde Child componenti render edip, ona parametre verebiliriz. "this.props.children" */}
                    <LoginPage/>

                </ApiProgress>
            </div>
        </div>
    );
}

export default App;
