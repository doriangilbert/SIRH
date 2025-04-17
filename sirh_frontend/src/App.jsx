import { useState, useEffect } from 'react'
import './App.css'
import LoginPage from './pages/LoginPage.jsx'
import MainPage from './pages/MainPage.jsx'
import DataPage from './pages/DataPage.jsx'
import MakeLeaveRequestPage from './pages/MakeLeaveRequestPage.jsx'
import ReviewLeaveRequestsPage from "./pages/ReviewLeaveRequestsPage.jsx";

function App() {
    const [employeeId, setEmployeeId] = useState(localStorage.getItem('employeeId') || '')
    const [currentPage, setCurrentPage] = useState('main')

    useEffect(() => {
        if (!employeeId) {
            setCurrentPage('login')
        }
    }, [employeeId])

    return (
        <div className="App">
            {currentPage === 'login' && (
                <LoginPage setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            )}
            {currentPage === 'main' && employeeId && (
                <MainPage
                    employeeId={employeeId}
                    setEmployeeId={setEmployeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
            {currentPage === 'data' && employeeId && (
                <DataPage
                    employeeId={employeeId}
                    setEmployeeId={setEmployeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
            {currentPage === 'makeLeaveRequest' && employeeId && (
                <MakeLeaveRequestPage
                    employeeId={employeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
            {currentPage === 'reviewLeaveRequests' && employeeId && (
                <ReviewLeaveRequestsPage
                    employeeId={employeeId}
                    setCurrentPage={setCurrentPage}
                />
            )}
        </div>
    )
}

export default App