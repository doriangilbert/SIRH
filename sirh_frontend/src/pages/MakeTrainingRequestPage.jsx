import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Header from '../components/Header.jsx'
import DataTable from '../components/DataTable.jsx'

const MakeTrainingRequestPage = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    const [trainings, setTrainings] = useState([])
    const [trainingId, setTrainingId] = useState('')
    const [error, setError] = useState(null)
    const [successMessage, setSuccessMessage] = useState('')

    useEffect(() => {
        axios.get('http://localhost:8080/trainings')
            .then((response) => {
                setTrainings(response.data)
                setError(null)
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while fetching trainings.')
            })
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault()
        if (!trainingId) {
            setError('Please enter a valid training ID.')
            return
        }

        axios.post(`http://localhost:8080/employees/${employeeId}/trainingrequests`, {
            trainingId: parseInt(trainingId),
        })
            .then(() => {
                setSuccessMessage('Training request sent successfully.')
                setError(null)
                setTrainingId('')
            })
            .catch((err) => {
                setError(err.response?.data || 'An error occurred while sending the request.')
                setSuccessMessage('')
            })
    }

    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            <div className="flex flex-col items-center justify-center">
                <h1 className="text-3xl mb-6">Make training request</h1>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                {successMessage && <div className="text-green-500 mb-4">{successMessage}</div>}
                <form onSubmit={handleSubmit} className="flex flex-col items-center">
                    <div className="mb-4">
                        <label htmlFor="trainingId" className="block mb-2">Training ID:</label>
                        <input
                            type="text"
                            id="trainingId"
                            value={trainingId}
                            onChange={(e) => setTrainingId(e.target.value)}
                            placeholder="Training ID"
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
                <h2 className="text-2xl mt-6 mb-4">Available trainings</h2>
                {trainings.length === 0 ? (
                    <div>No training available.</div>
                ) : (
                    <DataTable data={trainings} />
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

export default MakeTrainingRequestPage