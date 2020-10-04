import React from 'react';
import {Button} from "primereact/button";

const ButtonWithProgress = (props) => {

    const {onClick, pendingApiCall, disabled, text} = props;

    return (
        <div className="text-center">
            <Button
                className="btn btn-primary"
                label={text} onClick={onClick}
                disabled={disabled}>
                {/*{this.state.pendingApiCall && <ProgressSpinner className="p-progress-circle"/>}*/}
                {pendingApiCall && <span className="spinner-border spinner-border-sm"/>}
            </Button>
        </div>
    );
};

export default ButtonWithProgress;