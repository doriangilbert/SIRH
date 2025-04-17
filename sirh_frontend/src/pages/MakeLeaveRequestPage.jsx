import React, {useState} from 'react'
import axios from 'axios'
import Header from "../components/Header.jsx";

const MakeLeaveRequestPage = ({employeeId, setEmployeeId, setCurrentPage}) => {
    const [startDate, setStartDate] = useState('')
    const [endDate, setEndDate] = useState('')
    const [error, setError] = useState(null)
    const [successMessage, setSuccessMessage] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault()
        if (!startDate || !endDate) {
            setError('Please fill in both start and end dates.')
            return
        }

        axios.post(`http://localhost:8080/employees/${employeeId}/leaverequests`, {
            startDate,
            endDate
        })
            .then(() => {
                setSuccessMessage('Leave request sent successfully.')
                setError(null)
                setStartDate('')
                setEndDate('')
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while sending the request.')
                setSuccessMessage('')
            })
    }

    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage}/>
            <div className="flex flex-col items-center justify-center">
                <h1 className="text-3xl mb-6">Make a leave request</h1>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                {successMessage && <div className="text-green-500 mb-4">{successMessage}</div>}
                <form onSubmit={handleSubmit} className="flex flex-col items-center">
                    <div className="mb-4">
                        <label htmlFor="startDate" className="block mb-2">Start date :</label>
                        <input
                            type="date"
                            id="startDate"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                            className="border border-gray-300 rounded px-2 py-1"
                        />
                    </div>
                    <div className="mb-4">
                        <label htmlFor="endDate" className="block mb-2">End date :</label>
                        <input
                            type="date"
                            id="endDate"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                            className="border border-gray-300 rounded px-2 py-1"
                        />
                    </div>
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                    >
                        Send request
                    </button>
                </form>
                <button
                    onClick={() => setCurrentPage('main')}
                    className="mt-4 bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
                >
                    Back
                </button>
            </div>
        </div>
    )
}

export default MakeLeaveRequestPage