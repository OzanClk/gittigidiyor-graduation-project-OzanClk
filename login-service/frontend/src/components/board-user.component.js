import React, {Component} from "react";
import {isEmail, isAlpha, isMobilePhone} from "validator";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import Form from "react-validation/build/form";

const required = value => {

    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
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

const identificationValid = value => {
    const idLastDigit = value % 2;
    if (value.length < 11 || value.length > 11 || idLastDigit == 1 || !/^[0-9]+$/.test(value)) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid Identification Number.The last digit cannot be an odd number.
            </div>
        );
    }
};

const monthlyIncomeValid = value => {

    if (value < 0) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid Monthly Income.
            </div>
        );
    }
};

const phone = value => {
    if (!isMobilePhone(value, 'tr-TR')) {
        return (
            <div className="alert alert-danger" role="alert">
                This is not a valid phoneNumber.Please check.
            </div>
        );
    }
};

export default class BoardUser extends Component {
    constructor(props) {
        super(props);

        this.state = {
            content: "",
            firstname: "",
            lastname: "",
            identificationNumber: "",
            phoneNumber: "",
            monthlyIncome: Number,
            resultMessage: "",
            result: false,
            loading: false,
            message: ""
        };


        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeIdentificationHandler = this.changeIdentificationHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.changeMonthlyIncomeHandler = this.changeMonthlyIncomeHandler.bind(this);
        this.applyCredit = this.applyCredit.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

    }

    applyCredit = (e) => {

        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            console.log("BURADA1");
            let creditApplication = {
                firstname: this.state.firstname,
                lastname: this.state.lastname,
                identificationNumber: this.state.identificationNumber,
                phoneNumber: this.state.phoneNumber,
                monthlyIncome: this.state.monthlyIncome
            }
            UserService.applyCredit(creditApplication).then(res => {
                //this.props.history.push('/user');
                this.props.history.push(`/previous-creditApplications/${this.state.identificationNumber}`);
            })
            console.log(creditApplication);
            /*  UserService.applyCredit(creditApplication).then(res => {
                  this.state.message = res.data;
              })*/

        } else {
            this.setState({
                loading: false
            });
            console.log("BURADA3");
        }


    }

    handleLogin(e) {
        e.preventDefault();
    }

    async componentDidMount() {

        UserService.getUserBoard().then(
            response => {
                this.setState({
                    content: response.data
                });
            },
            error => {
                this.setState({
                    content:
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString()
                });

                if (error.response && error.response.status === 401) {
                    EventBus.dispatch("logout");
                }
            }
        );
    }


    changeFirstNameHandler = (e) => {
        this.setState({firstname: e.target.value});
    }
    changeLastNameHandler = (e) => {
        this.setState({lastname: e.target.value});
    }
    changeIdentificationHandler = (e) => {
        this.setState({identificationNumber: e.target.value});
    }
    changePhoneNumberHandler = (e) => {
        this.setState({phoneNumber: e.target.value});
    }
    changeMonthlyIncomeHandler = (e) => {
        this.setState({monthlyIncome: e.target.value});
    }


    previousCreditApplications(identificationNumber) {
        console.log(identificationNumber);
        this.props.history.push(`/previous-creditApplications/${identificationNumber}`);
    }


    render() {

        return (
            <div className="col-md-12">
                <div className="card card-container">
                    <h3 className="text-center">Credit Application Form</h3>
                    <Form
                        onSubmit={this.handleLogin}
                        ref={c => {
                            this.form = c;
                        }}
                    >
                        <div className="form-group">
                            <label htmlFor="firstName">First Name</label>
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
                            <label htmlFor="Lastname">Last Name</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="LastName"
                                value={this.state.lastname}
                                onChange={this.changeLastNameHandler}
                                validations={[required, alpha]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="identificationNumber">Identification Number</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="identificationNumber"
                                value={this.state.identificationNumber}
                                onChange={this.changeIdentificationHandler}
                                validations={[required, identificationValid]}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="phoneNumber">Phone Number</label>
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
                            <label htmlFor="monthlyIncome">Monthly Income</label>
                            <Input
                                className="form-control"
                                name="monthlyIncome"
                                value={this.state.monthlyIncome}
                                onChange={this.changeMonthlyIncomeHandler}
                                validations={[required, monthlyIncomeValid]}
                            />
                        </div>

                        <div className="form-group">
                            <button
                                className="btn btn-success btn-block"
                                disabled={this.state.loading}
                                onClick={this.applyCredit}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Apply</span>
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
                    <Form>
                        <div className="form-group">
                            <button
                                className="btn btn-primary btn-block"
                                disabled={this.state.loading}
                                onClick={() => this.previousCreditApplications(this.state.identificationNumber)}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Results</span>

                            </button>
                            <Input
                                type="text"
                                className="form-control"
                                name="identificationNumber"
                                value={this.state.identificationNumber}
                                onChange={this.changeIdentificationHandler}
                                placeHolder="Identification Number"
                                validations={[identificationValid]}
                            />

                        </div>
                    </Form>

                </div>

            </div>
        );
    }
}
