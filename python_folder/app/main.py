from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import numpy as np
from pathlib import Path

app = FastAPI()

# ----------------------
# LOAD MODEL (ONCE)
# ----------------------
MODEL_PATH = Path(__file__).parent / "model.pkl"

with open(MODEL_PATH, "rb") as f:
    model = joblib.load(f)

# ----------------------
# INPUT SCHEMA
# ----------------------
class CreditData(BaseModel):
    LIMIT_BAL: float
    SEX: int
    EDUCATION: int
    MARRIAGE: int
    AGE: int
    PAY_0: int
    PAY_2: int
    PAY_3: int
    PAY_4: int
    PAY_5: int
    PAY_6: int
    BILL_AMT1: float
    BILL_AMT2: float
    BILL_AMT3: float
    BILL_AMT4: float
    BILL_AMT5: float
    BILL_AMT6: float
    PAY_AMT1: float
    PAY_AMT2: float
    PAY_AMT3: float
    PAY_AMT4: float
    PAY_AMT5: float
    PAY_AMT6: float


# ----------------------
# CREDIT SCORING ENDPOINT
# ----------------------
@app.post("/credit-score")
def credit_score(data: CreditData):

    features = np.array([[
        data.LIMIT_BAL,
        data.SEX,
        data.EDUCATION,
        data.MARRIAGE,
        data.AGE,
        data.PAY_0,
        data.PAY_2,
        data.PAY_3,
        data.PAY_4,
        data.PAY_5,
        data.PAY_6,
        data.BILL_AMT1,
        data.BILL_AMT2,
        data.BILL_AMT3,
        data.BILL_AMT4,
        data.BILL_AMT5,
        data.BILL_AMT6,
        data.PAY_AMT1,
        data.PAY_AMT2,
        data.PAY_AMT3,
        data.PAY_AMT4,
        data.PAY_AMT5,
        data.PAY_AMT6
    ]])

    # Probability of DEFAULT (class 1)
    probability = model.predict_proba(features)[0][1]

    decision = "REJECT" if probability >= 0.7 else "APPROVE"

    return {
        "probability_of_default": round(float(probability), 3),
        "decision": decision
    }
