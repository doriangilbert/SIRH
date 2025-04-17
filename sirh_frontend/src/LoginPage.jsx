import React, { useState } from 'react'
import axios from 'axios'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

const LoginPage = ({ setEmployeeId, setCurrentPage }) => {
    const [inputEmployeeId, setInputEmployeeId] = useState('')

    const handleLogin = () => {
        if (inputEmployeeId.trim()) {
            axios.get(`http://localhost:8080/employees/${inputEmployeeId}`)
                .then(() => {
                    localStorage.setItem('employeeId', inputEmployeeId)
                    setEmployeeId(inputEmployeeId)
                    setCurrentPage('main')
                    alert(`Connected with employee ID: ${inputEmployeeId}`)
                })
                .catch(() => {
                    alert('Employee ID not found.')
                })
        } else {
            alert('Please enter a valid employee ID.')
        }
    }

    const handleInit = () => {
        axios.post('http://localhost:8080/init')
            .then(() => {
                alert('Successfully initialized.')
            })
            .catch(() => {
                alert('Initialization failed.')
            })
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
            <div className="mb-4"></div>
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
                className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mb-4"
            >
                Login
            </button>
            <button
                onClick={handleInit}
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
                Init
            </button>
        </div>
    )
}

export default LoginPage