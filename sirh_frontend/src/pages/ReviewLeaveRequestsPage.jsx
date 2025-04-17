import React, { useEffect, useState } from 'react'
import axios from 'axios'
import DataTable from '../components/DataTable.jsx'
import Header from "../components/Header.jsx";

const ReviewLeaveRequestsPage = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    const [leaveRequests, setLeaveRequests] = useState([])
    const [error, setError] = useState(null)
    const [requestId, setRequestId] = useState('')

    useEffect(() => {
        axios.get('http://localhost:8080/leaverequests')
            .then((response) => {
                const filteredRequests = response.data.filter(
                    (request) => request.reviewer === parseInt(employeeId)
                )
                setLeaveRequests(filteredRequests)
                setError(null)
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while fetching leave requests.')
            })
    }, [employeeId])

    const handleDecision = (decision) => {
        if (!requestId) {
            setError('Please enter a valid request ID.')
            return
        }

        axios.put(`http://localhost:8080/leaverequests/${requestId}/${decision}`, {
            reviewerId: employeeId
        })
            .then(() => {
                setLeaveRequests((prevRequests) =>
                    prevRequests.filter((request) => request.id !== parseInt(requestId))
                )
                setRequestId('')
                setError(null)
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while updating the request.')
            })
    }

    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            <div className="flex flex-col items-center justify-center">
                <h1 className="text-3xl mb-6">Review leave requests</h1>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                <div className="mb-4 flex items-center">
                    <input
                        type="text"
                        value={requestId}
                        onChange={(e) => setRequestId(e.target.value)}
                        placeholder="Request ID"
                        className="border border-gray-300 rounded px-2 py-1 mr-2"
                    />
                    <button
                        onClick={() => handleDecision('approve')}
                        className="bg-green-500 hover:bg-green-700 text-white font-bold py-1 px-2 rounded mr-2"
                    >
                        Approve
                    </button>
                    <button
                        onClick={() => handleDecision('refuse')}
                        className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded"
                    >
                        Refuse
                    </button>
                </div>
                {leaveRequests.length === 0 ? (
                    <div>No leave request to review.</div>
                ) : (
                    <DataTable data={leaveRequests} />
                )}
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

export default ReviewLeaveRequestsPage