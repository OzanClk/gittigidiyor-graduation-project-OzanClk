import React, {Component} from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import UserService from "../services/user.service";
import {isEmail, isAlpha,isMobilePhone} from "validator";

const required = value => {

    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

const email = value => {
    if (!isEmail(value)) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid email.
            </div>
        );
    }
};

const alpha = value => {
    if (!isAlpha(value)) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid character.Unused number and other characters.
            </div>
        );
    }
};

const phone = value => {
    if (!isMobilePhone(value,'tr-TR')) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid phoneNumber.Please check.
            </div>
        );
    }
};


class CreateCustomerComponent extends Component {


    constructor(props) {
        super(props)

        this.state = {

            id: this.props.match.params.id,
            firstname: '',
            lastname: '',
            email: '',
            phoneNumber: '',
            country: '',
            city: '',
            district: '',
            loading: false,
            message: ""
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.changeCountryHandler = this.changeCountryHandler.bind(this);
        this.changeCityHandler = this.changeCityHandler.bind(this);
        this.changeDistrictHandler = this.changeDistrictHandler.bind(this);
        this.saveCustomer = this.saveCustomer.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    saveCustomer = (e) => {
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            let customer = {
                firstname: this.state.firstname,
                lastname: this.state.lastname,
                email: this.state.email,
                phoneNumber: this.state.phoneNumber,
                address: {
                    country: this.state.country,
                    city: this.state.city,
                    district: this.state.district
                }
            };
            console.log(JSON.stringify(customer));

            UserService.saveCustomers(customer).then(res => {
                this.props.history.push('/admin')
            })
        } else {
            this.setState({
                loading: false
            });
        }
    }


    changeFirstNameHandler = (e) => {
        this.setState({firstname: e.target.value});
    }

    changeLastNameHandler = (e) => {
        this.setState({lastname: e.target.value});
    }

    changeEmailHandler = (e) => {
        this.setState({email: e.target.value});
    }
    changePhoneNumberHandler = (e) => {
        this.setState({phoneNumber: e.target.value});
    }
    changeCountryHandler = (e) => {
        this.setState({country: e.target.value});
    }
    changeCityHandler = (e) => {
        this.setState({city: e.target.value});
    }
    changeDistrictHandler = (e) => {
        this.setState({district: e.target.value});
    }


    cancel() {
        this.props.history.push("/admin")
    }

    handleLogin(e) {
        e.preventDefault();
    }


    render() {
        return (
            <div className="col-md-12">
                <div className="card card-container">
                    <h3 className="text-center">Add Customer</h3>
                    <Form
                        onSubmit={this.handleLogin}
                        ref={c => {
                            this.form = c;
                        }}
                    >
                        <div className="form-group">
                            <label htmlFor="firstname">FirstName</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="firstname"
                                value={this.state.firstname}
                                onChange={this.changeFirstNameHandler}
                                validations={[required, alpha]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastname">LastName</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="lastname"
                                value={this.state.lastname}
                                onChange={this.changeLastNameHandler}
                                validations={[required, alpha]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="email"
                                value={this.state.email}
                                onChange={this.changeEmailHandler}
                                validations={[required, email]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="phoneNumber">PhoneNumber</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="phoneNumber"
                                value={this.state.phoneNumber}
                                onChange={this.changePhoneNumberHandler}
                                validations={[required, phone]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="country">Country</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="country"
                                value={this.state.country}
                                onChange={this.changeCountryHandler}
                                validations={[required, alpha]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="city">City</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="city"
                                value={this.state.city}
                                onChange={this.changeCityHandler}
                                validations={[required, alpha]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="district">District</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="district"
                                value={this.state.district}
                                onChange={this.changeDistrictHandler}
                                validations={[required, alpha]}
                            />
                        </div>

                        <div className="form-group">
                            <button
                                className="btn btn-primary btn-block"
                                disabled={this.state.loading}
                                onClick={this.saveCustomer}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Save</span>
                            </button>


                        </div>
                        <div className="form-group">
                            <button
                                className="btn btn-danger btn-block"
                                disabled={this.state.loading}
                                onClick={this.cancel.bind(this)}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Cancel</span>
                            </button>


                        </div>

                        {this.state.message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {this.state.message}
                                </div>
                            </div>
                        )}
                        <CheckButton
                            style={{display: "none"}}
                            ref={c => {
                                this.checkBtn = c;
                            }}
                        />
                    </Form>
                </div>
            </div>
        );
    }
}

export default CreateCustomerComponent;