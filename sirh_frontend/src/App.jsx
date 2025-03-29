import {useEffect, useState} from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios'

function App() {
    const [employees, setEmployees] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8080/employees')
            .then(response => {
                console.log(response.data)
                setEmployees(response.data)
            })
            .catch(error => {
                console.error('Error :', error)
            })
    }, [])

    return (
        <div>
            <div className="flex items-center justify-center py-5">
                <a href="https://vite.dev" target="_blank">
                    <img src={viteLogo} className="logo" alt="Vite logo"/>
                </a>
                <a href="https://react.dev" target="_blank">
                    <img src={reactLogo} className="logo react" alt="React logo"/>
                </a>
            </div>
            <div className="h-screen flex flex-col items-center justify-center">
                <h1 className="text-5xl">SIRH</h1>
                <table className="table-auto border-collapse border border-gray-400 mt-5">
                    <thead>
                    <tr>
                        <th className="border border-gray-300 px-4 py-2">ID</th>
                        <th className="border border-gray-300 px-4 py-2">Prénom</th>
                        <th className="border border-gray-300 px-4 py-2">Nom</th>
                    </tr>
                    </thead>
                    <tbody>
                    {employees.map(employee => (
                        <tr key={employee.id}>
                            <td className="border border-gray-300 px-4 py-2">{employee.id}</td>
                            <td className="border border-gray-300 px-4 py-2">{employee.firstName}</td>
                            <td className="border border-gray-300 px-4 py-2">{employee.lastName}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default App