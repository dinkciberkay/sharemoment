import React from "react";
import {InputText} from "primereact/inputtext";

const Input = (props) => {
    const {error, name, onChange, value} = props;
    const className = error ? 'form-control is-invalid' : 'form-control';
    return (
        <div>
            <InputText
                className={className}
                name={name}
                value={value}
                onChange={onChange}/>
            <div className="invalid-feedback">{error}</div>
        </div>
    )

};


export default Input;