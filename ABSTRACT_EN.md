# Talk Abstract

Testing is a wide field, with lots of weird concepts not much discussed in the industry.

This talk means to introduce:

* example based testing and how it falls short
* property based testing (PBT) and strategies to identify useful properties
* metamorphic testing for when PBT is too expensive

# Talk Description

Testing is a hard and, to be blunt, often boring task, that most developers do because we must, when we do it at all.

This reputation as being something that’s neither fun nor cool means that industry developers often miss out on the ideas academia has to alleviate these issues. And academia has lots of ideas!

The purpose of this talk is to go through 3 main testing methodologies, from the most common (and worst) to the most esoteric I know of. Along the way, we’ll explain and formalise the issues we must solve when testing, and how to tackle them in different scenarios.

We intend to focus on two main methodologies:

* property based testing, which is more and more accepted but also very often misused, and how to use it properly
* metamorphic testing, which is basically unknown but very powerful for the scenarios in which it’s applicable

At the end of the talk, attendants should have learned strategies to write property based tests that are easy to read and maintain, and far more useful then the usual strategy of let’s just rewrite the system under test. They should also a good idea of what metamorphic testing is and the knowledge required to start using it in their own code base straight away.

# Misc.

I believe this is a worthy subject because while there are lots of talks about property based tests, I’ve yet to see one that tackles strategies for property identification. This is far more important, in my opinion, than the nitty gritty details of random test case generation, which, while important, is merely a technical detail.

Metamorphic testing can be a hugely powerful testing methodology (close to a 100 confirmed new bugs discovered in GCC!), is relatively unknown, and really should be talked about more.

I have been using property based tests heavily, and badly, for years. I feel I’m well qualified to talk about the subject because I know precisely how to get it wrong, and I’ve painfully worked on learning how to do it right. And metamorphic testing, well. There are very few people qualified to talk about the subject, given that it’s all but unknown outside of academia, and I’ve researched the subject thoroughly and have been experimenting with it for a while now.
