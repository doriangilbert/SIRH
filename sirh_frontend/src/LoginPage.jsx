import React, { useState } from 'react'
import axios from 'axios'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

const LoginPage = ({ setEmployeeId }) => {
    const [inputEmployeeId, setInputEmployeeId] = useState('')

    const handleLogin = () => {
        if (inputEmployeeId.trim()) {
            axios.get(`http://localhost:8080/employees/${inputEmployeeId}`)
                .then(() => {
                    localStorage.setItem('employeeId', inputEmployeeId)
                    setEmployeeId(inputEmployeeId)
                    alert(`Connected with employee ID : ${inputEmployeeId}`)
                })
                .catch(() => {
                    alert('Invalid employee ID. Please try again.')
                })
        } else {
            alert('Please enter a valid employee ID.')
        }
    }

    return (
        <div className="h-screen flex flex-col items-center justify-center">
            <div className="flex items-center">
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo" />
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo" />
                </a>
            </div>
            <h1 className="text-5xl">SIRH</h1>
            <br/>
            <h3 className="text-2xl mb-6">Login</h3>
            <input
                type="text"
                placeholder="Employee ID"
                value={inputEmployeeId}
                onChange={(e) => setInputEmployeeId(e.target.value)}
                className="border border-gray-300 rounded px-2 py-1 mb-4"
            />
            <button
                onClick={handleLogin}
                className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
            >
                Login
            </button>
        </div>
    )
}

export default LoginPage