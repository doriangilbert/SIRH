import { useState, useEffect } from 'react'
import './App.css'
import LoginPage from './LoginPage'
import MainPage from './MainPage'

function App() {
    const [employeeId, setEmployeeId] = useState(localStorage.getItem('employeeId') || '')

    useEffect(() => {
        if (employeeId) {
            console.log(`Employé connecté avec l'ID : ${employeeId}`)
        }
    }, [employeeId])

    return (
        <div className="App">
            {!employeeId ? (
                <LoginPage setEmployeeId={setEmployeeId} />
            ) : (
                <MainPage employeeId={employeeId} setEmployeeId={setEmployeeId} />
            )}
        </div>
    )
}

export default App