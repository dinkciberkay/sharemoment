import React from "react";
import {InputText} from "primereact/inputtext";

const Input = (props) => {
    const {label, error, name, onChange, value} = props;
    const className = error ? 'form-control is-invalid' : 'form-control';
    return (
        <div>
            <InputText
                className={className}
                label={label}
                name={name}
                value={value}
                onChange={onChange}/>
            <div className="invalid-feedback">{error}</div>
        </div>
    )

};


export default Input;